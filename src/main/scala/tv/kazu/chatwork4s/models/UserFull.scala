package tv.kazu.chatwork4s.models

case class UserFull(
  account_id: Int,
  room_id: Int,
  name: String,
  chatwork_id: String,
  organization_id: Int,
  organization_name: String,
  department: String,
  title: String,
  url: String,
  introduction: String,
  mail: String,
  tel_organization: String,
  tel_extension: String,
  tel_mobile: String,
  skype: String,
  facebook: String,
  twitter: String,
  avatar_image_url: String
)
