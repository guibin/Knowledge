package guibin.zhang.datastructure

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack
import scala.collection.mutable.Queue

class BinaryNode(private var data: Option[Any],
                 private var left: Option[BinaryNode],
                 private var right: Option[BinaryNode]) {

  var level = 0
  var horizonIndex = 0
  var verticalIndex = 0
  
  def this(data: Any) = this(Some(data), None, None)
  def this() = this(None, None, None)
  
  //Can be set to 0, 1, 2;
  //0 just visited, 1 returned from left, 2 returned from right
  private var mark: Int = -1

  def getData = this.data
  
  def setData(newData: Any): Unit = newData match {
    case null => data = None
    case x => data = Some(x)
  }

  def getLeftChild = this.left

  def getRightChild = this.right

  def setLeftChild(leftChild: Option[BinaryNode]): Unit = this.left = leftChild

  def setRightChild(rightChild: Option[BinaryNode]): Unit = this.right = rightChild

  def hasLeftChild: Boolean = left.isDefined

  def hasRightChild: Boolean = right.isDefined

  def isLeaf: Boolean = left.isEmpty && right.isEmpty

  def setMark(newMark: Int) = this.mark = newMark

  def getMark = this.mark

  def copy: BinaryNode = {
    val newRoot = new BinaryNode

    newRoot
  }

  def inorderTraverse {
    val stack = Stack[BinaryNode]()
    val result = ListBuffer[BinaryNode]()

    var currentNode: Option[BinaryNode] = Some(this)

    while(currentNode.isDefined || !stack.isEmpty) {
      //Push all the left child into the stack
      while(currentNode.isDefined) {
        stack.push(currentNode.get)
        currentNode = currentNode.get.getLeftChild
      }

      //Pop the stack, visit current and its right child.
      if(!stack.isEmpty) {
        currentNode = Some(stack.pop)
        
        result += currentNode.get
        currentNode = currentNode.get.getRightChild
      }
    }

    //Output the result
    println("InOrder Result: " + result.map(r => r.getData.get).mkString(","))
  }

  def recursiveInorderTraverse {
    
    val result = ListBuffer[BinaryNode]()
    recursiveInorderTraverse(Some(this))
    println("RecursiveInOrder Result: " + result.map(r => r.getData.get).mkString(","))
    
    def recursiveInorderTraverse(node: Option[BinaryNode]) {
      if(node.isDefined) {
        recursiveInorderTraverse(node.get.getLeftChild)
        result += node.get
        recursiveInorderTraverse(node.get.getRightChild)
      }
    }
  }

  def breadthFirstTraverse {
    val queue = Queue[BinaryNode]()
    val result = ListBuffer[BinaryNode]()

    //Add the root to the queue
    var currentNode: Option[BinaryNode] = Some(this)
    queue += currentNode.get

    //Dequeue, visit current, then add its left and right child into queue
    while(!queue.isEmpty) {
      currentNode = Some(queue.dequeue)
      result += currentNode.get

      if(currentNode.get.getLeftChild.isDefined) queue += currentNode.get.getLeftChild.get
      if(currentNode.get.getRightChild.isDefined) queue += currentNode.get.getRightChild.get
    }

    println("BreadthFirstTraverse Result: " + result.map(r => r.getData.get).mkString(","))
  }

  
  def printTree(totalLevel: Int) {
    //Construct a two dimention array
    val arrayWidth = scala.math.pow(2, totalLevel) - 1
    val arr = Array.fill(totalLevel, arrayWidth.toInt)("")
    
    breadthFirstTraverse
    
    def breadthFirstTraverse {
      val queue = Queue[BinaryNode]()
      val result = ListBuffer[BinaryNode]()

      //Add the root to the queue
      var currentNode: Option[BinaryNode] = Some(this)
      currentNode.get.level = 1
      currentNode.get.verticalIndex = 0
      currentNode.get.horizonIndex = arrayWidth.toInt/2

      queue += currentNode.get

      //Dequeue, visit current, then add its left and right child into queue
      while(!queue.isEmpty) {
        currentNode = Some(queue.dequeue)
        result += currentNode.get
        arr(currentNode.get.verticalIndex)(currentNode.get.horizonIndex) = currentNode.get.getData.get.toString

        if(!currentNode.get.getLeftChild.isDefined && !currentNode.get.getRightChild.isDefined) {
          //Continue
        } else {
          if(currentNode.get.getLeftChild.isDefined) {
            val leftChild = currentNode.get.getLeftChild
            leftChild.get.level = currentNode.get.level + 1
            leftChild.get.verticalIndex = currentNode.get.verticalIndex + 1
            leftChild.get.horizonIndex = currentNode.get.horizonIndex - 1
            queue += leftChild.get
          } else {
            val newNode = new BinaryNode("*")
            newNode.level = currentNode.get.level + 1
            newNode.verticalIndex = currentNode.get.verticalIndex + 1
            newNode.horizonIndex = currentNode.get.horizonIndex - 1
            queue += newNode
          }

          if(currentNode.get.getRightChild.isDefined) {
            val rightChild = currentNode.get.getRightChild
            rightChild.get.level = currentNode.get.level + 1
            rightChild.get.verticalIndex = currentNode.get.verticalIndex + 1
            rightChild.get.horizonIndex = currentNode.get.horizonIndex + 1
            queue += currentNode.get.getRightChild.get
          } else {
            val newNode = new BinaryNode("*")
            newNode.level = currentNode.get.level + 1
            newNode.verticalIndex = currentNode.get.verticalIndex + 1
            newNode.horizonIndex = currentNode.get.horizonIndex + 1
            queue += newNode
          }
        }
      }

      println("Printing indice >> " + result.map(r => r.getData.get + "(" + r.horizonIndex + "," + r.verticalIndex + ")").mkString(","))

      var counter = 0
      var graph = new StringBuffer
      while(counter < totalLevel) {
        graph.append(arr(counter).mkString("-")).append("\n")
        counter += 1
      }
      println(graph)
    }

  }

  def preorderTraverse {
    val stack = Stack[BinaryNode]()
    val result = ListBuffer[BinaryNode]()

    var currentNode: Option[BinaryNode] = Some(this)

    while(currentNode.isDefined || !stack.isEmpty){
      while(currentNode.isDefined) {
        result += currentNode.get

        stack.push(currentNode.get)
        currentNode = currentNode.get.getLeftChild
      }

      if(!stack.isEmpty) {
        currentNode = Some(stack.pop)
        currentNode = currentNode.get.getRightChild
      }
    }

    println("PreorderTraverse Result: " + result.map(r => r.getData.get).mkString(","))
  }

  def recursivePreorderTraverse {

    val result = ListBuffer[BinaryNode]()
    recursivePreorderTraverse(Some(this))
    println("RecursivePreOrder Result: " + result.map(r => r.getData.get).mkString(","))

    def recursivePreorderTraverse(node: Option[BinaryNode]) {
      if(node.isDefined) {
        result += node.get
        recursivePreorderTraverse(node.get.getLeftChild)
        recursivePreorderTraverse(node.get.getRightChild)
      }
    }
  }

  def postorderTraverse {
    val stack = Stack[BinaryNode]()
    val result = ListBuffer[BinaryNode]()

    var currentNode: Option[BinaryNode] = Some(this)

    while(currentNode.isDefined || !stack.isEmpty) {
      //Push all the left child into stack and marked as 1
      while(currentNode.isDefined) {
        currentNode.get.setMark(1)
        stack.push(currentNode.get)

        currentNode = currentNode.get.getLeftChild
      }

      //Visit the one marked as 2
      while(!stack.isEmpty && stack.head.getMark == 2) {
        val r = stack.pop
        result += r
      }

      if(!stack.isEmpty) {
        currentNode = Some(stack.head)
        currentNode.get.setMark(2)

        currentNode = currentNode.get.getRightChild
      }
    }

    println("PostorderTraverse Result: " + result.map(r => r.getData.get).mkString(","))
  }

  def recursivePostorderTraverse {

    val result = ListBuffer[BinaryNode]()
    recursivePostorderTraverse(Some(this))
    println("RecursivePostOrder Result: " + result.map(r => r.getData.get).mkString(","))

    def recursivePostorderTraverse(node: Option[BinaryNode]) {
      if(node.isDefined) {
        recursivePostorderTraverse(node.get.getLeftChild)
        recursivePostorderTraverse(node.get.getRightChild)
        result += node.get
      }
    }
  }

  
}

object BinaryNode {
  def main(args: Array[String]) {
    println("Begin to run BiaryTree:")
    val treeA = init

    treeA.printTree(4)

    treeA.inorderTraverse
    treeA.recursiveInorderTraverse
    treeA.breadthFirstTraverse
    treeA.preorderTraverse
    treeA.recursivePreorderTraverse
    treeA.postorderTraverse
    treeA.recursivePostorderTraverse

    val treeB = rebuildBinaryTree("ABDECFIGH".toArray, "DBEAIFGCH".toArray)
    if(treeB.isDefined) {
      println("#######Rebuild Result######")
      treeB.get.printTree(4)
    }
  }


  /**
   *                  A
   *                /   \
   *               B     C
   *              / \   / \
   *             D  E  F   H
   *                  / \
   *                 I   G
   * inOrder: 	DBEAIFGCH
   * preOrder:	ABDECFIGH
   * postOrder:	DEBIGFHCA
   * breadthFirst:ABCDEFHIG
   */
  def init: BinaryNode = {
    val treeD = new BinaryNode("D")

    val treeE = new BinaryNode("E")
    val treeB = new BinaryNode("B")
    treeB.setLeftChild(Some(treeD))
    treeB.setRightChild(Some(treeE))

    val treeG = new BinaryNode("G")
    val treeF = new BinaryNode("F")
//    val treeI = new BinaryNode("I")
//    treeF.setLeftChild(Some(treeI))
    treeF.setRightChild(Some(treeG))

    val treeC = new BinaryNode("C")
    val treeH = new BinaryNode("H")
    treeC.setLeftChild(Some(treeF))
    treeC.setRightChild(Some(treeH))

    val treeA = new BinaryNode("A")
    treeA.setLeftChild(Some(treeB))
    treeA.setRightChild(Some(treeC))

    treeA
  }

  def rebuildBinaryTree(preOrderResult: Array[Char], inOrderResult: Array[Char]) = {
    var result: Option[BinaryNode] = None
    
    val stack = Stack[(Boolean, Array[Char], Option[BinaryNode])]()//true=>left, false=>right
    
    if(!preOrderResult.isEmpty) {
      //Construct the root of the tree
      val root = Some(new BinaryNode(preOrderResult(0)))
      result = root
      
      val index = inOrderResult.indexOf(preOrderResult(0))
      if(index > -1) {
        val leftSubInOrderResult = inOrderResult.slice(0, index)
        val rightSubInOrderResult = inOrderResult.slice(index + 1, inOrderResult.length)

        //Push to the stack
        stack.push((false, rightSubInOrderResult, root))
        stack.push((true, leftSubInOrderResult, root))
      }

      
      var nextPreOrderResult = preOrderResult.slice(1, preOrderResult.length)
      while(!stack.isEmpty && !nextPreOrderResult.isEmpty) {
        
        val (isLeft, subInOrderResult, currentNode) = stack.pop
        
        val node = Some(new BinaryNode(nextPreOrderResult(0)))
        
        if(isLeft) {
          currentNode.get.setLeftChild(node)
          println(currentNode.get.getData + " 's left Child " + node.get.getData)
        } else {
          currentNode.get.setRightChild(node)
          println(currentNode.get.getData + " 's right Child " + node.get.getData)
        }
        
        if(subInOrderResult.length > 0) {
          val index = subInOrderResult.indexOf(nextPreOrderResult(0))
          if(index > -1) {
            val leftSubInOrderResult = subInOrderResult.slice(0, index)
            val rightSubInOrderResult = subInOrderResult.slice(index + 1, inOrderResult.length)
            if(rightSubInOrderResult.length > 0) stack.push((false, rightSubInOrderResult, node))
            if(leftSubInOrderResult.length > 0) stack.push((true, leftSubInOrderResult, node))
          }
        } 

        nextPreOrderResult = nextPreOrderResult.slice(1, nextPreOrderResult.length)
      }
    }
    
    result
  }
}
