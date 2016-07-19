---
title: Hello world!
author: Dag
date: 2016-07-24 10:15:30
type: post
categories: post,first
---

## @post.title
by @post.author @ @post.date
in @post.categories

Welcome to the first post of Pagery.

Pagery is a Java based static web page generator created because
I found the ones already in existence to be way to complex with
to much functionality and very little focus on leveraging the already
excellent pipelines we as Java developers enjoy every day.

#### Goals for Pagery
1. Support webjars
2. Create and implement a very small and specific subset of Markdown called "pagedown"
3. Create a simple template language embeddable in Markdown called "markers"


#### Template language
These are some of the supported template markers.
```
@post.all
@post.one
@post.end
```




