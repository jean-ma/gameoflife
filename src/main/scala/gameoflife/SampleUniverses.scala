package gameoflife

object SampleUniverses {
  val glider: String =
    """
      |x = 134, y = 114
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

  val compactShip: String =
    """
      |#N LWSS tagalong
      |#O David Bell
      |#C A tagalong for two lightweight, middleweight, or heavyweight spaces
      |#C hips.
      |#C www.conwaylife.com/wiki/index.php?title=Lightweight_spaceship
      |x = 25, y = 19
      |21bo3b$18b4o3b$13bo2bob2o5b$13bo11b$4o8bo3bob2o5b$o3bo5b2ob2obobob5o$o
      |9b2obobobo2b5o$bo2bo2b2o2bo3b3o2bob2ob$6bo2bob2o12b$6bo4b2o12b$6bo2bob
      |2o12b$bo2bo2b2o2bo3b3o2bob2ob$o9b2obobobo2b5o$o3bo5b2ob2obobob5o$4o8bo
      |3bob2o5b$13bo11b$13bo2bob2o5b$18b4o3b$21bo!
    """.stripMargin

  val gliderDestruction: String =
    """
      |#N Gosper glider gun (glider destruction)
      |#O Dean Hickerson
      |#C Complete destruction of Gosper glider gun with two gliders
      |#C Glider destruction of the Gosper glider gun.
      |#C http://www.conwaylife.com/wiki/Gosper_glider_gun
      |#C http://www.conwaylife.com/patterns/gosperglidergungliderdestruction.rle
      |x = 47, y = 26
      |bo$2bo$3o6$15bo$15b4o$16b4o10b2o$5b2o9bo2bo9bobo$5b2o9b4o8b3o8b2o$15b
      |4o8b3o9b2o$15bo12b3o$29bobo$30b2o7$45b2o$44b2o$46bo!
    """.stripMargin

  val smallerPeriodGlider: String =
    """
      |#N twogun
      |#O V. Everett Boyer and Doug Petrie
      |#C The smallest known period-60 gun; it uses two copies of the Gosper
      |#C glider gun.
      |x = 39, y = 27
      |27bo11b$25bobo11b$15b2o6b2o12b2o$14bo3bo4b2o12b2o$3b2o8bo5bo3b2o14b$3b
      |2o8bo3bob2o4bobo11b$13bo5bo7bo11b$14bo3bo20b$15b2o22b$26bo12b$27b2o10b
      |$26b2o11b4$21b2o16b$9bobo10b2o15b$9bo2bo8bo17b$2o10b2o11b2o12b$2o8bo3b
      |2o8bobo12b$5b2o5b2o9bo6b2o7b$4bo4bo2bo10bo2bo2bo2bo6b$9bobo11bo6b3o6b$
      |24bobo5b3o4b$25b2o6bobo3b$35bo3b$35b2o!
    """.stripMargin

  val spaceship: String =
    """
      |#N 56P6H1V0
      |#O Hartmut Holzwart
      |#C Period 6 c/6 orthogonal spaceship. Discovered in May 2009.
      |#C www.conwaylife.com/wiki/index.php?title=56P6H1V0
      |x = 26, y = 12
      |5b3o10b3o5b$3obo7b2o7bob3o$4bo3bo2bo2bo2bo3bo4b$4bo5bo4bo5bo4b$10b2o2b
      |2o10b$7bo3bo2bo3bo7b$7bobo6bobo7b$8b10o8b$10bo4bo10b$8bo8bo8b$7bo10bo
      |7b$8bo8bo!
    """.stripMargin

  val all = Vector(glider, spaceship, smallerPeriodGlider, gliderDestruction)
}
