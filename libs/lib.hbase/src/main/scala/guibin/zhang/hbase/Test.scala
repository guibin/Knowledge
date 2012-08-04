package guibin.zhang.hbase

import org.apache.hadoop.hbase.util.Bytes

object Test {
  def main(args: Array[String]) {
    val l = 1234567890L
    val lb = Bytes.toBytes(l)
    println(lb.length)
    
    val s = "" + l
    val sb = Bytes.toBytes(s)
    println(sb.length)
  }
}
