name := "oap-perf-suite"

version := "1.0"

scalaVersion := "2.11.12"

/* For oap 0.5.0 package, there are two guava packages.
 * One is from Spark. The other is from ORC.
 * With Oap for Spark 2.3, publicsuffix package has two copies as well.
 * Use below strategy to select the first matching guava and publicsuffix packages.
 */
assemblyMergeStrategy in assembly := {
  case PathList("com", "google", "thirdparty", "publicsuffix", xs @ _*) => MergeStrategy.first
  case PathList("com", "google", "guava", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

unmanagedJars in Compile += file("/home/oap/oap_jars/oap.jar")
unmanagedJars in Compile += file("lib/spark-sql-perf_2.11-0.4.11-SNAPSHOT.jar")

// With Oap for Spark 2.3, it needs to change below spark version from 2.1.0 to 2.3.0.
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0" % "provided"
libraryDependencies += "org.reflections" % "reflections" % "0.9.10" % "compile"
