val inputTask = tasks.register("inputTask", Copy::class.java) {
  from("input.txt")
  into("$buildDir/input")
}

tasks.register("repro") {
  inputs.files(files(inputTask))
  doFirst {
    delete("$buildDir/output")
    repeat(properties["n"]?.toString()?.toInt() ?: 50000) { i ->
      file("$buildDir/input/input.txt").copyTo(file("$buildDir/output/$i.txt"))
    }
  }
}

