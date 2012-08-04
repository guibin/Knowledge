package guibin.zhang.datastructure

object LinkedList {
  
  
  class Node[T](var data: T, var next: Node[T]) {
    def this(data: T) {
      this(data, null)
    }
    
    override def toString(): String = data.toString
  }
  
  /**
   * Find the middle items of the linked singly list.
   */
  def middleItemOf(root: Node[String]): List[Node[String]] = {
    
    var head = root
    var middle = root
    var counter = 0
    
    while(head.next != null) {
      counter += 1
      head = head.next
      if(counter % 2 == 0) {
        middle = middle.next
      }
    }
    
    if(counter % 2 == 0) {
      return List(middle)
    } else {
      return List(middle, middle.next)
    }
  }
  
  def printList(root: Node[String]) {
    var sb = new StringBuilder
    var n = root
    while(n.next != null) {
      sb.append(n.data).append(" -> ")
      n = n.next
    }
    sb.append(n.data)
    println(sb.toString)
  }
  
  def main(args: Array[String]) {
    val j = new Node("J")
    val i = new Node("I", j)
    val h = new Node("H", i)
    val g = new Node("G", h)
    val f = new Node("F", g)
    val e = new Node("E", f)
    val d = new Node("D", e)
    val c = new Node("C", d)
    val b = new Node("B", c)
    val a = new Node("A", b)
    
    printList(a)
    middleItemOf(a).foreach(x => println("middle item is " + x))
  }
}


