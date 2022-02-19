package com.adamcs.formula1.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "item")
class News {
    @field:Element(name = "title", required = false)
    lateinit var title: String
    @field:Element(name = "description", required = false)
    var description: String = ""
    @field:Element(name = "link", required = false)
    lateinit var link: String

    override fun toString(): String {
        return "News: (title = $title, description = $description)"
    }
}
