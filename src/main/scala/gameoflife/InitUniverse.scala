package gameoflife

/**
 *
 */
object InitUniverse {
  val glider: String =
    """
      |21b2o2b2o80b2o2b2o$21b2o2b2o80b2o2b2o$20b3o2b3o78b3o2b3o$20b3o2b3o78b
      |3o2b3o2$20bo6bo78bo6bo$20bo2b2o2bo78bo2b2o2bo$21b6o80b6o$4b2o2b2o114b
      |2o2b2o$4b2o2b2o114b2o2b2o$3b3o2b3o12b2o84b2o12b3o2b3o$3b3o2b3o11bo2bo
      |82bo2bo11b3o2b3o$22b4o82b4o$3bo6bo10b2o2b2o80b2o2b2o10bo6bo$3bo2b2o2bo
      |7bo2bo4bo2bo74bo2bo4bo2bo7bo2b2o2bo$4b6o11bo4bo80bo4bo11b6o$22b4o82b4o
      |$18bo10bo74bo10bo$6b2o11bo8bo76bo8bo11b2o$5bo2bo116bo2bo$5b4o14b2o84b
      |2o14b4o$4b2o2b2o114b2o2b2o$bo2bo4bo2bo108bo2bo4bo2bo$4bo4bo114bo4bo$5b
      |4o21bo94b4o$bo10bo16b2o90bo10bo$2bo8bo17bobo90bo8bo2$6b2o118b2o21$3b2o
      |2b2o116b2o2b2o$3b2o2b2o116b2o2b2o$2b3o2b3o114b3o2b3o$2b3o2b3o114b3o2b
      |3o2$2bo6bo114bo6bo$2bo2b2o2bo114bo2b2o2bo$3b6o116b6o3$5b2o120b2o$4bo2b
      |o118bo2bo$4b4o118b4o$3b2o2b2o116b2o2b2o$o2bo4bo2bo110bo2bo4bo2bo$3bo4b
      |o116bo4bo$4b4o118b4o$o10bo110bo10bo$bo8bo112bo8bo2$5b2o120b2o3$6b2o2b
      |2o110b2o2b2o$6b2o2b2o110b2o2b2o$5b3o2b3o108b3o2b3o$5b3o2b3o108b3o2b3o
      |2$5bo6bo22b2o2b2o52b2o2b2o22bo6bo$5bo2b2o2bo22b2o2b2o52b2o2b2o22bo2b2o
      |2bo$6b6o22b3o2b3o50b3o2b3o22b6o$34b3o2b3o50b3o2b3o2$8b2o24bo6bo50bo6bo
      |24b2o$7bo2bo23bo2b2o2bo50bo2b2o2bo23bo2bo$7b4o24b6o52b6o24b4o$6b2o2b2o
      |110b2o2b2o$3bo2bo4bo2bo104bo2bo4bo2bo$6bo4bo25b2o56b2o25bo4bo$7b4o25bo
      |2bo54bo2bo25b4o$3bo10bo21b4o54b4o21bo10bo$4bo8bo21b2o2b2o52b2o2b2o21bo
      |8bo$32bo2bo4bo2bo46bo2bo4bo2bo$8b2o25bo4bo52bo4bo25b2o$14b2o2b2o16b4o
      |54b4o16b2o2b2o$14b2o2b2o12bo10bo46bo10bo12b2o2b2o$13b3o2b3o12bo8bo48bo
      |8bo12b3o2b3o$13b3o2b3o92b3o2b3o$37b2o56b2o$13bo6bo92bo6bo$13bo2b2o2bo
      |92bo2b2o2bo$14b6o94b6o3$16b2o98b2o$15bo2bo96bo2bo$15b4o96b4o$14b2o2b2o
      |94b2o2b2o$11bo2bo4bo2bo88bo2bo4bo2bo$14bo4bo94bo4bo$15b4o96b4o$11bo10b
      |o88bo10bo$12bo8bo90bo8bo2$16b2o98b2o!
    """.stripMargin

  val cells: String =
    """
      |136b2o$136b2o5b2o$143b2o3$112b2o27b2o$112b2o5bob2o18b2o$119b2obo24b2o$
      |147b2o$106b2o22b3o$106b2o20bo4bo$110b2o18bob2o$110b2o15b3o$126bo$146b
      |2o$146bo$105b2o40bo$105b2o39b2o7$15b2o76b2o$15b2o76b2obo$97bo$94bo$95b
      |ob2o$97b2o2$152b2o$7b2o143b2o$7b2o3$147b2o$147b2o$151b2o$18b3o130b2o$
      |17bo2bo89b2o$17bo3bo88b2o24bob2o$16b2o2bo84bo10b2o18b2obo5b2o$3bo14bo
      |85bobo9b2o27b2o$3bobo13b2o82bo3bo$3bo2bo12b3o80bo3bo$5bo13b3o79bo3bo8b
      |2o$3b2o95bo3bo9b2o5b2o$5bo95bobo17b2o$5b3o94bo$6bo11b3o6bo$3bobo19b2o$
      |bo2bo23b2o$o25bobo$bobo23b2o$28bo$28bobo$27bo2bo$27b2o$27bo$23b3o9$42b
      |2o$47bo$41bob5o$40b3o2bo3bo$46bobo$39bo8bo$39bo$39bo2$53b2o3bo$49bo3b
      |3obob2o$49bo3b3o4bo$49bo6bo3bo$57b3o$57bo16b2o$74b2o7$66b2o$50bo15b2o$
      |49b2o3bo$48bob2obobo$47bo4bo$45bo2bo3bob3o2$45bobo$46bo!
    """.stripMargin

  val compactCodership: String =
    """
      |56bo$55b3o$54b3obo14bo$53boo3boo6bobbo3bo$52boobobboo6bo3bobbo$53boobo
      |boo5bo$54b3oboo6boboo$56bo4bo$57b4o20boo$59bo12boboo5boo$72b3o$73bo$$
      |80b3o$85b3o$79bobbo3boo$80b5obobboo$80boobo3boobo$81boo4b3o$81bobobob
      |oo$61boo13boo$61boo4bo7bo$66b3o6b3o$48b3o15bobbo5boo$47bo19boo6bo$46bo
      |4boo13bo$45bo3bo18bo$45bobbo4bo12bo$45bo3bo3bo12b3o$46boobob3o$26bo22b
      |o$25b3o22b4o$24b3obo14bo8boo$23boo3boo6bobbo3bo$22boobobboo6bo3bobbo$
      |23booboboo5bo$24b3oboo6boboo$26bo4bo$27b4o$29bo12boboo$42b3o$43bo9$31b
      |oo$31boo$39boo$obbo35boo$4bo$o3bo$b4o!
    """.stripMargin

  val parasites: String =
    """
      |6bo13bo$5b3o11b3o$3b2ob3o9b3ob2o$4bo2bob2o5b2obo2bo$b2obo4bobo3bobo4bo
      |b2o$b2obobo2bob2ob2obo2bobob2o$bo8bobobobo8bo$2o7b2obobob2o7b2o$12bobo
      |$7b3obo3bob3o$6b2o11b2o$6bo5bo4b2o2bo$5b2o4b3o3b2o2bo$11bob2o3b3o$12b
      |3o4bo$12b3o$12b3o$12b2o2$19bobo$20b2o$15b2o3bo$16b2o$15bo!
    """.stripMargin

  val all = Vector(glider, cells, compactCodership, parasites)
}
