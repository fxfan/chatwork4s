package com.everforth.cw4s

class ApiErrorException(val errors: List[String]) extends Exception(errors.mkString(","))
