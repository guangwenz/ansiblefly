package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}

class JobController @Inject()(cc:ControllerComponents) extends AbstractController(cc){
  def index = Action {implicit request=>
    Ok(views.html.job.index())
  }

}
