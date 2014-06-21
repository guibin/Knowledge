package guibin.zhang.codetest

import scala.collection.mutable._

case class Node(var name: String, var perm: Permission, var meta: Metadata, var childern: ListBuffer[Node]) {
  
  override def toString: String = {
    perm.toString + "\t" + meta.toString + "\t" + name
  }
}

case class Metadata(var createTime: Long, var lastModifiedTime: Long, var tpe:Tpe, var numberOfChildren: Int) {
  
  override def toString: String = {
    numberOfChildren + "\t" + tpe.toString + "\t" + lastModifiedTime
  }
}

//This is the node type, suppose it can be folder, file, soft link, hard link and so on. Each type has a id and name.
case class Tpe(var name: String, var id: Int) {
  override def toString: String = {
    name
  }
}

case class DirType extends Tpe("dir", 1) {
  override def toString: String = {
    "dir"
  }
}

case class FileType extends Tpe("file", 2) {
  override def toString: String = {
    "file"
  }
}
  

case class Permission(val permCode: Int, var owner: String, var group: String) {
  override def toString: String = {
    permCode + "\t" + owner + "\t" + group
  }
}



  
  
  

