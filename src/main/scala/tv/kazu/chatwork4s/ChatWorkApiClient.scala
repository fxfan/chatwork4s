package tv.kazu.chatwork4s

import play.api.libs.json.JsNull
import tv.kazu.chatwork4s.models._

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.{HttpOptions, HttpRequest, Http}
import scala.concurrent.ExecutionContext.Implicits.global

import ChatWorkApiResponse.Implicits._

trait ChatWorkApiClientBase {
  val apiBase = "https://api.chatwork.com/v2"

  protected val apiToken: String
  protected val connTimeoutMs: Int
  protected val readTimeoutMs: Int

  def me(): Future[UserFull] = {
    get("/me").map { response =>
      response.as[UserFull]
    }
  }

  def contacts(): Future[List[User]] = {
    get("/contacts").map { response =>
      response.as[List[User]]
    }
  }

  def room(roomId: Int): Future[Room] = {
    roomOrErrors(roomId).map(throwLeft(_))
  }

  def roomOrErrors(roomId: Int): Future[Either[List[String], Room]] = {
    get("/rooms/" + roomId).map { response =>
      response.asEither[Room]
    }
  }

  def rooms(): Future[List[Room]] = {
    roomsOrErrors().map(throwLeft(_))
  }

  def roomsOrErrors(): Future[Either[List[String], List[Room]]] = {
    get("/rooms").map { response =>
      response.asEither[List[Room]]
    }
  }

  def roomMembers(roomId: Int): Future[List[User]] = {
    roomMembersOrErrors(roomId).map(throwLeft(_))
  }

  def roomMembersOrErrors(roomId: Int): Future[Either[List[String], List[User]]] = {
    get(s"/rooms/${roomId}/members").map { response =>
      response.asEither[List[User]]
    }
  }

  def messages(roomId: Int, fetchNext: Boolean = false): Future[List[Message]] = {
    messagesOrErrors(roomId, fetchNext).map(throwLeft(_))
  }

  def messagesOrErrors(roomId: Int, fetchNext: Boolean = false): Future[Either[List[String], List[Message]]] = {
    val params = if (fetchNext) Seq() else Seq(("force", "1"))
    get(s"/rooms/${roomId}/messages", params).map { response =>
      if (response.httpResponse.isSuccess) {
        if (response.jsValue != JsNull) {
          Right(response.as[List[Message]])
        } else {
          Right(Nil)
        }
      } else {
        response.asEither[List[Message]] // This should return Left[List[String]]
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

  private def throwLeft[T](either: Either[List[String], T]): T = {
    either match {
      case Left(list) =>
        throw new ApiErrorException(list)
      case Right(x) => x
    }
  }
}

class ChatWorkApiClient(val apiToken: String, protected val connTimeoutMs: Int = 1000 * 5, protected val readTimeoutMs: Int = 1000 * 180)
  extends ChatWorkApiClientBase {

}
