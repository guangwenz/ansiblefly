package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}

class JobTemplateController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index()=Action {implicit request=>
    Ok(views.html.job_template.index())
  }
}
