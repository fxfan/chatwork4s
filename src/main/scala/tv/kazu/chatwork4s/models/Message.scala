package tv.kazu.chatwork4s.models

case class Message (
  messageId: Int,
  account: UserMini,
  body: String,
  sendTime: Int,
  updateTime: Int
)