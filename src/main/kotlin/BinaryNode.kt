typealias Visitor<T> = (T) -> Unit

class BinaryNode<T: Any>(var value: T){
    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null
}