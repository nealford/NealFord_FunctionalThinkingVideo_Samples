package com.nealford.ft.polydispatch

class LetterGrades {
  def static gradeFromScore(score) {
    switch (score) {
      case 90..100 : return "A"
      case 80..<90 : return "B"
      case 70..<80 : return "C"
      case 60..<70 : return "D"
      case 0..<60  : return "F"
      case ~"[ABCDFabcdf]" : return score.toUpperCase()
      default: throw new IllegalArgumentException("Invalid score: ${score}")
    }
  }
}

println "grade for 91 is ${LetterGrades.gradeFromScore(91)}"
