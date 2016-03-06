package tv.kazu.chatwork4s.models

case class Room(
  room_id: Int,
  name: String,
  `type`: String,
  role: String,
  sticky: Boolean,
  unread_num: Int,
  mention_num: Int,
  mytask_num: Int,
  message_num: Int,
  file_num: Int,
  task_num: Int,
  icon_path: String,
  last_update_time: Int
)
