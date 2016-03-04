//val sentenceSplitter = MLSentenceSegmenter.bundled().get
import epic.models.{NerSelector, ParserSelector}
import epic.preprocess



val text = "This is a test"

object Parser {
  val parser = ParserSelector.loadParser().get
}

val reportSentences = List(
  "This is a sentence filled with text"
)
def parse(text: String) = {
  println(text)
  val preprocessed = preprocess.preprocess(text)
  try {
    val parsed = preprocessed.par.map(Parser.parser).seq
    for ((tree, sentence) <- parsed zip preprocessed) {
      println(tree render sentence)
      //    println("Tree: "+ tree + tree.getClass())
      //    println("Sentence: " + sentence)
      tree.allChildren.toList.map(
        x =>
          if (x.label.label == "NP") {
            //          println(
            //            "Value: "+ x.label.label+ " "+ x.span.begin +" "+ x.span.end + " " + sentence.slice(x.span.begin, x.span.end)
            //          )
            println(sentence.slice(x.span.begin, x.span.end).mkString((" ")))
          }
      )
      //val render =  tree render sentence
      //println(render.getClass())
    }
  }
  catch {
    case gc: java.lang.OutOfMemoryError => println( gc.printStackTrace() + "Caused by: " + text)
  }
}

//reportSentences.map( s=>  println("---------------------------------------------------------" + parse(s)))

