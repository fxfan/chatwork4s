package com.everforth.cw4s

import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatest.{FlatSpec, Matchers}

class ChatWorkApiClientSpec extends FlatSpec with Matchers {

  val token = sys.env("CHATWORK_API_TOKEN")
  val roomId = sys.env("CHATWORK_ROOM_ID").toInt
  val client = ChatWorkApiClient(token)

  "room" should "return a room object" in {
    client.room(roomId).foreach { room =>
      room.roomId shouldBe roomId
      room.name shouldBe sys.env("ROOM_NAME")
    }
  }

  "me" should "return " in {
    client.me().foreach { me =>
      me.name shouldBe sys.env("ME_NAME")
    }
  }

  "postMessage" should "post a message with mentions" in {
    client.postMessage(roomId, "テストだよ！")
    val contactIds = Seq(sys.env("CONTACT_ID_1").toInt, sys.env("CONTACT_ID_2").toInt)
    client.contacts().foreach { contacts =>
      client.postMessage(roomId, "テストだよ！", contacts.filter(u => contactIds.contains(u.accountId)))
    }
  }

}
