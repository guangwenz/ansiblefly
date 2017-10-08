package controllers

import java.net.URL
import javax.inject._

import play.api._
import play.api.data.{Form, FormError}
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.data.format.Formats._
import play.api.mvc._

@Singleton
class ProjectController @Inject()(cc: ControllerComponents) extends AbstractController(cc)
  with play.api.i18n.I18nSupport {

  import ProjectController._

  implicit object UrlFormatter extends Formatter[URL] {
    override val format: Option[(String, Seq[Any])] = Some(("format.url", Nil))

    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], URL] = parsing(new URL(_), "error.url", Nil)(key, data)

    override def unbind(key: String, value: URL): Map[String, String] = Map(key -> value.toString)
  }

  val projectForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "description" -> optional(text),
      "scm" -> of[URL]
    )(ProjectData.apply)(ProjectData.unapply)
  )

  def index() = Action { implicit request =>
    Ok(views.html.project.index(projectForm))
  }

  def create() = Action { implicit request =>
    projectForm.bindFromRequest().fold(
      formWithErrors =>
        Ok(views.html.project.index(formWithErrors))
      ,
      data => {
        println(data)
        Redirect(routes.ProjectController.index())
      }
    )
  }
}

object ProjectController {



  case class ProjectData(name: String, description: Option[String], scm: java.net.URL)

}