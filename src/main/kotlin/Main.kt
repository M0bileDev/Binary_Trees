fun main() {
    val zero = BinaryNode(0)
    val one = BinaryNode(1)
    val five = BinaryNode(5)
    val seven = BinaryNode(7)
    val eight = BinaryNode(8)
    val nine = BinaryNode(9)
    val ten = BinaryNode(10)
    val eleven = BinaryNode(11)

    seven.leftChild = one
    seven.rightChild = nine
    nine.leftChild = eight
//    nine.rightChild = ten
    one.leftChild = zero
    one.rightChild = five
//    eight.leftChild = eleven

    val tree = seven
    println(tree)

    println("Tree has been traversed in order")
    tree.traverseInOrder { println(it) }

    println("Tree has been traversed pre order")
    tree.traversePreOrder { println(it) }

    println("Tree has been traversed post order")
    tree.traversePostOrder { println(it) }

    println("Tree has height of ${tree.heightOfTheTree()}")

    println("Tree is going to be serialize and deserialize")
    println(tree)
    val array = tree.serialize()
    println("Tree has been serialized: $array")
    println("Tree has been deserialized:")
    println(tree.deserialize(array))
}