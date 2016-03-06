package tv.kazu.chatwork4s

import play.api.libs.json.JsNull
import tv.kazu.chatwork4s.models._

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.{HttpOptions, HttpRequest, Http}
import scala.concurrent.ExecutionContext.Implicits.global

import ChatWorkApiResponse.Implicits._

trait ChatWorkApiClientBase {
  val apiBase = "https://api.chatwork.com/v1"

  protected val apiToken: String
  protected val connTimeoutMs: Int
  protected val readTimeoutMs: Int

  def me(): Future[UserFull] = {
    get("/me").map { response =>
      response.jsValue.as[UserFull]
    }
  }

  def contacts(): Future[Seq[User]] = {
    get("/contacts").map { response =>
      response.jsValue.as[Seq[User]]
    }
  }

  def rooms(): Future[Seq[Room]] = {
    get("/rooms").map { response =>
      response.jsValue.as[Seq[Room]]
    }
  }

  def roomMembers(roomId: Int): Future[Seq[User]] = {
    get(s"/rooms/${roomId}/members").map { response =>
      response.jsValue.as[Seq[User]]
    }
  }

  def messages(roomId: Int, fetchNext: Boolean = false) = {
    val params = if (fetchNext) Seq() else Seq(("force", "1"))
    get(s"/rooms/${roomId}/messages", params).map { response =>
      if (response.jsValue != JsNull) {
        response.jsValue.as[Seq[Message]]
      } else {
        Seq()
      }
    }
  }

  def get(path: String, params: Seq[(String, String)] = Seq())(implicit ec: ExecutionContext = global): Future[ChatWorkApiResponse] = {
    Future {
      new ChatWorkApiResponse(getRequest(path, params).asString)
    }
  }

  def post(path: String, params: Seq[(String, String)])(implicit ec: ExecutionContext = global): Future[ChatWorkApiResponse] = {
    Future {
      new ChatWorkApiResponse(postRequest(path, params).asString)
    }
  }

  def getRequest(path: String, params: Seq[(String, String)]): HttpRequest = {
    baseHttpRequest(path).params(params)
  }

  def postRequest(path: String, params: Seq[(String, String)]): HttpRequest = {
    baseHttpRequest(path).postForm(params)
  }

  private def baseHttpRequest(path: String): HttpRequest = {
    if (!path.startsWith("/")) {
      throw new IllegalArgumentException(s"'path' must start with '/', but '$path' was passed.")
    }
    Http(apiBase + path).header("X-ChatWorkToken", apiToken).timeout(connTimeoutMs, readTimeoutMs)
  }
}

class ChatWorkApiClient(val apiToken: String, protected val connTimeoutMs: Int = 1000 * 5, protected val readTimeoutMs: Int = 1000 * 180)
  extends ChatWorkApiClientBase {

}
