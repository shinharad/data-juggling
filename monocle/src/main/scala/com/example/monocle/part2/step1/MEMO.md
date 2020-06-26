Iso       : Source => Target                          | Target => Source
Prism     : Source => Option[Target]                  | Target => Source
Lens      : Source => Target                          | (Target, Source) => Source
Optional  : Source => Option[Target]                  | (Target, Source) => Source
Traversal : (Source => Target, Source => Target, ...) | (Target, Target, ..., Source) => Source
Fold      : (Source => Target, Source => Target, ...)
