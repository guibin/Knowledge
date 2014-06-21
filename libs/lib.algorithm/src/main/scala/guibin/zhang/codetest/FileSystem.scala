package guibin.zhang.codetest

object FileSystem {
  def main(args: Array[String]) {
    println("Hi...")
    
    val l = scala.collection.mutable.ListBuffer[String]()
    l += "a"
    l.foreach(x => println(x))
  }
}

class FileSystem(node: Node) {
  
  var current: Node = node
  
  def cd(target: Node) {
    if(!current.childern.contains(target)) {
      println("No such directory.")
      return
    }
    
    current = target
  }
  
  def mkdir(target: Node):Boolean = {
    if(current.childern.contains(target)) {
      println(target.name + " already exists.")
      return false
    }
    else {
      //Initialize the target here
      
      //Just append it to childer of current
      current.childern :+ target
      return true
    }
    
  }
  
  def ls :String = {
    var msg = ""
    current.childern.foreach(x => msg = msg + x.toString + "\n")
    
    msg
  }
    
  def touch(target: Node):Boolean = {
    
    if(current.childern.contains(target)) {
      println(target.name + " already exists, cannot be created.")
      return false
    } else {
      current.childern :+ target
      return true
    }
  }
}
