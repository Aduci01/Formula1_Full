package com.adamcs.formula1.data.model

import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss")
data class NewsList(
    @Path("a/b[1]")
    val news: List<News>
)
