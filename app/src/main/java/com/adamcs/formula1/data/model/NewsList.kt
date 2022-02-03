package com.adamcs.formula1.data.model

import org.simpleframework.xml.*

@Root(strict = false, name = "rss")
class NewsList{
    @field:Element(name = "channel")
    lateinit var channel: Channel
}

@Root(strict = false, name = "channel")
class Channel {
    @field:ElementList(name = "item", inline = true)
    lateinit var news: List<News>
}
