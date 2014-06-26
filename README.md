vitzli's recipe dumper
======================

Simple Minecraft console application that dumps shaped and shapeless crafting recipes and ore dictionary.

Requirements
------------
* Minecraft 1.6.4 + MinecraftForge 9.11.1.965
* Google Guava 14 (most likely already installed)

Supported commands
------------------

```
/dumprecipes items - dump item recipes
/dumprecipes oredict - dump ore dictionary
/dumprecipes all - dump both item recipes and ore dictionary
/dumprecipes fluids - dump data from fluid registry
```

'dumps' directory must exist in the minecraft directory, following file are created:

* vsdump-timestamp-recipes.log - recipe data
* vsdump-timestamp-oredict.log - ore dictionary
* vsdump-timestamp-items.log - item IDs and metadata dump
* vsdump-timestamp-fluidreg.log - fluid registry data

See examples/ dir.

Download
--------

https://www.dropbox.com/s/esk2gjjmukdtsnd/vsRecipeDumper-0.0.5.zip

* md5: 6eb74f4cb10823918a449b06efdf4682
* sha1: 7e230e2884b8d0db373d70c6616d85eaff3f001c


License
-------

GPL v3, see COPYING
