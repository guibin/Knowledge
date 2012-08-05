package guibin.zhang.algorithm

import scala.collection.mutable.ListBuffer

object QuickSortS {
  
  def main(args: Array[String]) {
    val list = ListBuffer(20, 19, 35, 44, 17, 22, 1, 2, 78)
    val result = simpleVersion(list)
    println(list.mkString(",") + " => " + result.mkString(","))
  }
  
  /**
   * The simple version:
   * 1. Pick an element, called a pivot, from the list. We pick the first one of the list.
   * 2. Select and remove a pivot value(head) from the list
   * 3. Create empty lists 'less' and 'greater'
   * 4. Traverse the list, if 'x' ≤ 'pivot' then append 'x' to 'less', else append 'x' to 'greater'
   * 5. return concatenate(quicksort('less'), 'pivot', quicksort('greater')) 
   * 
   * Disadvantage of this simple version:
   * 1. it requires O(n) extra storage space, which is as bad as MergeSort.
   */
  def simpleVersion(list: ListBuffer[Int]): ListBuffer[Int] = {
    if(list.size <= 1) {
      return list //a list of zero or one element is already sorted.
    } else {
      //select and remove a pivot value(head) from the list
      val pivot = list.head
      val less = new ListBuffer[Int]
      val greater = new ListBuffer[Int]
      for(item <- list.drop(1)) {
        if(item <= pivot) {
          less += item
        } else {
          greater += item
        }
      }
      //Concate the less, pivot and greater
      return simpleVersion(less) ++ ListBuffer[Int](pivot) ++ simpleVersion(greater)
    }
  }
}
