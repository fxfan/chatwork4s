package tv.kazu.chatwork4s

class ApiErrorException(val errors: List[String]) extends Exception(errors.mkString(","))
