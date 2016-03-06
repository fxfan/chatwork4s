package tv.kazu.chatwork4s.models

case class UserFull(
  accountId: Int,
  roomId: Int,
  name: String,
  chatworkId: String,
  organizationId: Int,
  organizationName: String,
  department: String,
  title: String,
  url: String,
  introduction: String,
  mail: String,
  telOrganization: String,
  telExtension: String,
  telMobile: String,
  skype: String,
  facebook: String,
  twitter: String,
  avatarImageUrl: String
)
