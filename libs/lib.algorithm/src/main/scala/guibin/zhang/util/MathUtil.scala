package guibin.zhang.util

object MathUtil {
  
  /**
   * To find out whether a number is even or odd
   */
  def isEven(i: Int): Boolean = {
    if(i % 2 == 0) true else false
  }
  
  
  /**
   * To find out thether a number is a power of 2?
   * Divide the number by 2, to see whether it can meet 1 or not
   */
  def isPowerOf2(i: Int): Boolean = {
    
    if(i >= -1 && i <= 1) {
      println(i + " is not the number of a power of 2")
      return false
    }
    
    var tmp = i
    var counter = 0
    
    while(isEven(tmp)) {
      counter += 1
      tmp = tmp >> 1 
    }
    
    if(tmp == 1 || tmp == -1){
      println(i + " = 2^" + (counter + 1))
      true 
    }
    else {
      println(i + " is not the number of a power of 2, tmp=" + tmp)
      false
    }
  }
  
  def main(args: Array[String]) {
    isPowerOf2(32)
    isPowerOf2(64)
    isPowerOf2(34)
    isPowerOf2(28)
    isPowerOf2(-32)
    isPowerOf2(-64)
    isPowerOf2(-34)
    isPowerOf2(-28)
    isPowerOf2(0)
    isPowerOf2(1)
  }
}
