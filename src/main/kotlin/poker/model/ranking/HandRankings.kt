package poker.model.ranking

import poker.model.model.Card

private const val ALL_PICTURES_STRING = "2345678910JQKA"

fun List<Card>.isPair(): Boolean =
    this.groupBy { it.weight }.map { it.value }.any { it.size == 2 }

fun List<Card>.isTwoPair(): Boolean =
    this.groupBy { it.weight }.map { it.value }.filter { it.size == 2 }.count() >= 2

fun List<Card>.isTriple(): Boolean =
    this.groupBy { it.weight }.map { it.value }.any { it.size == 3 }

fun List<Card>.isStraight(): Boolean {
    val orderedList = this.sortedBy { it.weight.weightNumber }.distinctBy { it.weight.weightString }
    val orderedPictureLists = (0..orderedList.size - 5).map {
        (0..4).fold("",
            { accumulator, index -> "$accumulator${orderedList[it + index].weight.weightString}" }
        )
    }
    return orderedPictureLists.any { ALL_PICTURES_STRING.contains(it) }
}

fun List<Card>.isFlush(): Boolean =
    this.groupBy { it.suit }.entries.any { it.value.size >= 5 }

fun List<Card>.isFullHouse(): Boolean =
    this.groupBy { it.weight }.map { it.value }.filter { it.size == 2 }.count() >= 1
        && this.groupBy { it.weight }.map { it.value }.filter { it.size == 3 }.count() >= 1

fun List<Card>.isQuadruple(): Boolean =
    this.groupBy { it.weight }.map { it.value }.any { it.size == 4 }

fun List<Card>.isStraightFlush(): Boolean =
    this.groupBy { it.suit }.entries.filter {
        it.value.size >= 5
    }.any { it.value.isStraight() }
