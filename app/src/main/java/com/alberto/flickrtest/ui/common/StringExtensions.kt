package com.alberto.flickrtest.ui.common

fun String.addTags() =
    if (isNotEmpty()) "#".plus(replace(" ", "#")) else this