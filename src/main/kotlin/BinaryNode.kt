typealias Visitor<T> = (T) -> Unit

class BinaryNode<T : Any>(var value: T) {
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

    fun traverseInOrder(visit: Visitor<T>){
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }
}