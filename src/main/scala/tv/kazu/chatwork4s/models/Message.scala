package tv.kazu.chatwork4s.models

case class Message (
  message_id: Int,
  account: UserMini,
  body: String,
  send_time: Int,
  update_time: Int
)