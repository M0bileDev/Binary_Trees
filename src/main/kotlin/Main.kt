fun main() {
    val zero = BinaryNode(0)
    val one = BinaryNode(1)
    val five = BinaryNode(5)
    val seven = BinaryNode(7)
    val eight = BinaryNode(8)
    val nine = BinaryNode(9)

    seven.leftChild = one
    seven.rightChild = nine
    nine.leftChild = eight
    one.leftChild = zero
    one.rightChild = five

    val tree = seven
    println(tree)
    println("Tree has been traversed in order")
    tree.traverseInOrder { println(it) }
    println("Tree has been traversed pre order")
    tree.traversePreOrder { println(it) }
    println("Tree has been traversed post order")
    tree.traversePostOrder { println(it) }
}