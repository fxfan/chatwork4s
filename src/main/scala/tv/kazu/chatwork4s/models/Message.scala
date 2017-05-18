package tv.kazu.chatwork4s.models

/**
 * @param messageId String representation of Long value
 * @param account
 * @param body
 * @param sendTime
 * @param updateTime
 * @see [[https://help.chatwork.com/hc/ja/articles/115000019401]]
 */
case class Message (
  messageId: String,
  account: UserMini,
  body: String,
  sendTime: Int,
  updateTime: Int
)