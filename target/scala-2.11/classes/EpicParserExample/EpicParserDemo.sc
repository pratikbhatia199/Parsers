//val sentenceSplitter = MLSentenceSegmenter.bundled().get
import epic.models.{NerSelector, ParserSelector}
import epic.preprocess



val text = "This is a test"

object Parser {
  val parser = ParserSelector.loadParser().get
}

val reportSentences = List(
    "DATE OF SERVICE: 10/09/2015",
    "CHIEF COMPLAINT: The patient is a 77-year-old gentleman with stage IVprostate cancer, here for further evaluation and management.",
    "HISTORY OF PRESENT ILLNESS: The history of present illness is well characterized in the initial/consult note.",
    "ALLERGIES: Refer to the Allergy section within the Visit Navigator in the EMR.",
    "CURRENT MEDICATIONS: Refer to the Medication section within Chart Review in the EMR.",
    "The past medical history, past surgical history, social history,  occupational history, family history, allergies are all reviewed and  are unchanged from the initial/consult note of 07/14/2011.",
    "REVIEW OF SYSTEMS:",
    "Denies: Extremity swelling.",
    "Denies: Neurologic dysfunction.",
    "Denies: Dysuria or hematuria.",
    "Denies: Nausea, vomiting, melena.",
    "Denies: Chest pain.",
    "Denies: Cough, shortness of breath, hemoptysis.",
    "Denies: Adenopathy.",
    "Denies: Problems with vision.",
    "Denies: Skin rash.",
    "REVIEW OF SYSTEMS:",
    "Pupils are equal, round, react to light.",
    "ECOG/KPS: 1/80.",
    "PHYSICAL EXAMINATION:",
    "Respiratory: Clear to auscultation.",
    "Mouth is without lesions.",
    "Cardiac: Normal S1, S2, without murmurs or rubs.",
    "HEENT: Sclerae non-icteric.",
    "Normal bowel sounds.",
    "Abdomen: Soft, nontender.",
    "Neck: Supple.",
    "Vital signs: Refer to the Vitals review section within the Visit Navigator section in the EMR, including pain.",
    "No rales or rhonchi.",
    "Lymph: No palpable adenopathy in the cervical, axillary or inguinal regions.",
    "Skin: No rash.",
    "Liver, spleen not  enlarged.",
    "Musculoskeletal: No edema or disfigurement.",
    "Neurologic: Oriented x3.",
    "Motor exam normal.",
    "Sensory exam normal.",
    "Cranial nerve assessment is normal.",
    "Affect: Normal.",
    "LABS: Reviewed and enclosed in the Labs and/or Pathology tab within the Chart Review activity in the EMR.",
    "WBC 13.4, hemoglobin 9.3,  platelets 384,000.",
    "PSA 51.39 (was 49.53).",
    "A CBC (complete blood count with differential) was ordered to assess  white count, hemoglobin and platelet levels.",
    "Also to assess red cell  and platelet size and white count differential.",
    "Our patients with  serious blood disorders and active cancer, along with those who are  receiving or did receive prior chemotherapy or biologics, are at risk  of developing significant abnormality in blood counts that would  require immediate medical intervention including, but not limited to,  adjustment in drug dosing, transfusions, or further testing.",
    "IMAGING: Reviewed and enclosed in the Imaging tab within the Chart Review activity in the EMR.",
    "IMPRESSION: Stage IV prostate carcinoma, increasing pain, increasing fatigue secondary to Xtandi.",
    "PLAN: 1.",
    "Robert S.",
    "DD: 10/11/2015"
  )

def parse(text: String) = {
  println(text)
  val preprocessed = preprocess.preprocess(text)
  try {
    val parsed = preprocessed.par.map(Parser.parser).seq
    for ((tree, sentence) <- parsed zip preprocessed) {
      println("Sentence: -- "+ sentence)
      println(tree render sentence)
      tree.allChildren.toList.map(
        x =>
          if (x.label.label == "NP") {
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

reportSentences.map( s=>  println("---------------------------------------------------------" + parse(s)))

