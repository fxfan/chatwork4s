package com.everforth.chatwork4s.models

case class Room(
  roomId: Int,
  name: String,
  `type`: String,
  role: String,
  sticky: Boolean,
  unreadNum: Int,
  mentionNum: Int,
  mytaskNum: Int,
  messageNum: Int,
  fileNum: Int,
  taskNum: Int,
  iconPath: String,
  lastUpdateTime: Int,
  /**
   * /rooms/&lt;room id&gt; の時のみセットされる
   */
  description: Option[String]
)
