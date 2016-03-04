name := "Parsers"

version := "1.0"

scalaVersion := "2.11.7"


resolvers ++= Seq(
  "ScalaNLP Maven2" at "http://repo.scalanlp.org/repo",
  "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/",
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "org.scalanlp" %% "epic" % "0.2",
  "org.scalanlp" %% "epic-parser-en-span" % "2015.1.25",
  "org.scalanlp" %% "epic-pos-en" % "2015.1.25",
  "org.scalanlp" %% "epic-ner-en-conll" % "2015.1.25",
  "org.scalanlp" %% "english"  % "2015.1.25"
)


credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")


scalacOptions ++= Seq("-deprecation", "-language:_", "-optimize")

javaOptions += "-Xmx4g"

    