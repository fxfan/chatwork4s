package tv.kazu.chatwork4s.models

case class User(
  account_id: Int,
  /**
   * /rooms/{room_id}/members の場合は、None となる
   */
  room_id: Option[Int],
  name: String,
  chatwork_id: String,
  organization_id: Int,
  organization_name: String,
  department: String,
  avatar_image_url: String
)
