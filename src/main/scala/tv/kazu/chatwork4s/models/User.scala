package tv.kazu.chatwork4s.models

case class User(
  accountId: Int,
  /**
   * /rooms/{room_id}/members の場合は、None となる
   */
  roomId: Option[Int],
  name: String,
  chatworkId: String,
  organizationId: Int,
  organizationName: String,
  department: String,
  avatarImageUrl: String
)
