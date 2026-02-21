import kotlin.math.max

typealias Visitor<T> = (T?) -> Unit

class BinaryNode<T : Any?>(var value: T) {
    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    override fun toString() = diagram(this)

    private fun diagram(
        node: BinaryNode<T>?,
        top: String = "",
        root: String = "",
        bottom: String = ""
    ): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(
                    node.rightChild,
                    "$top  ",
                    "$top┌──",
                    "$top│ "
                ) + root + "${node.value}\n" +
                        diagram(
                            node.leftChild,
                            "$bottom│",
                            "$bottom└──",
                            "$bottom  "
                        )
            }
        } ?: "$root null\n"
    }

    /**
     * If the current node has a left child, recursively visit this child first.
     * Then visit the node itself.
     * If the current node has a right child, recursively visit this child.
     */
    fun traverseInOrder(visitor: Visitor<T>) {
        leftChild?.traverseInOrder(visitor)
        visitor(value)
        rightChild?.traverseInOrder(visitor)
    }

    /**
     * Visits the current node first.
     * Recursively visits the left and right child.
     */
    fun traversePreOrder(visitor: Visitor<T>) {
        visitor(value)
        leftChild?.traversePreOrder(visitor)
        rightChild?.traversePreOrder(visitor)
    }

    /**
     * Recursively visits the left and right child.
     * Only visits the current node after the left and right child have been visited recursively.
     */
    fun traversePostOrder(visitor: Visitor<T>) {
        leftChild?.traversePostOrder(visitor)
        rightChild?.traversePostOrder(visitor)
        visitor(value)
    }

    /**
     * Challenge 1 -> Given a binary tree, find the height of the tree.
     */
    fun heightOfTheTree(node: BinaryNode<T>? = this): Int {
        if (this.leftChild == null && this.rightChild == null) return 0
        return node?.let { 1 + max(node.heightOfTheTree(node.leftChild), node.heightOfTheTree(node.rightChild)) } ?: -1
    }

    //region Challenge2

    fun traversePreOrderWithNull(visitor: Visitor<T>) {
        visitor(value)
        leftChild?.traversePreOrderWithNull(visitor) ?: visitor(null)
        rightChild?.traversePreOrderWithNull(visitor) ?: visitor(null)
    }

    fun serialize(node: BinaryNode<T> = this): MutableList<T?> {
        val list = mutableListOf<T?>()
        node.traversePreOrderWithNull { list.add(it) }
        return list
    }

    fun deserialize(list: MutableList<T?>): BinaryNode<T?>? {
        // 1
        val rootValue = list.removeFirst() ?: return null

        // 2
        val root = BinaryNode<T?>(rootValue)

        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)

        return root
    }

    //endregion
}