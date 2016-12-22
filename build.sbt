
name := "crispy-qdsl"
organization := "com.crispywalrus"
scalaVersion := "2.11.8"
scalaOrganization := "org.typelevel"
scalacOptions ++= Seq(
  "-encoding","UTF-8",
  "-deprecation",
  "-language:_",
  "-Xfuture",
  "-Ypartial-unification"
)

libraryDependencies += "io.getquill" %% "quill-finagle-mysql" % "1.0.1"



