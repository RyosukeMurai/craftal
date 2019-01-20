package useCase.event.request

case class CreateEventRequest(title: String,
                              description: String,
                              statusId: Int,
                              locationId: Int) {

}
