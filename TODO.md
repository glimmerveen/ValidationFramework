# FIXES

* Fix calls to setVisible() on icon decoration
* Icon tip decoration a bit more on the up and right when using Nimbus LAF
* Fix moving panels on Linux when using the icon decoration on the tabbed pane
* Remaining icontip's tooltips when quickly moving the mouse (in tables and outside tables): only on Linux?
* Icon decoration steals mouse event even outside clipping bounds
* Icontip dialogs can be made Popups (just like JPopupMenu or regular tooltips): not mishandled by some window managers?

# NEW STUFF

* User-modification trigger for (formatted) textfields
* IconResultHandler: dynamic message (maybe override the getInvalidText() method)
* Consistent comments (use of dispose(), cast transformers, default behaviors, what classes can be used for, etc.)
* Allow disabled decoration on tab?
* Allow disabled decoration on cell?
* Table model change trigger
* Table editing started/stopped/canceled triggers
* Table checkbox editor trigger
* Table editing: with/without valid commit to model, column-wise validator, per-cell/per-row group validation
* More consistency for handling "no result" results
* Initial state/trigger when building the validators
* Fix ToolTipDialog transparency support (Java 6, Java 7 and JNA)
* Label boolean feedback
* Mappable validator builder
* Manual trigger or trigger() method on the validator? How about the mappable validator?
* Common settings (default icons, etc.)
* Rule-based formatter
* Reformat/Revert JFormattedTextField when focus lost? Save values allowed by the rules (wider than the default number formatter)
* Icon decoration above combobox popups
* JavaFX 2 support
* Tab decoration depending on tab selection
* No decoration on disabled components (config + listen to component state)
* Avoid instantiation of result collectors (collect directly from validators, etc.)
* Better icon tip anchoring for IconTipDecorator
* Alternative location of icon tip w.r.t. screen edge
* Common implementation for the icon tip
* Auto-scroll/jump to errors in case of scrollpanes, tabbedpane, etc.
* Initial focus for JFormattedTextField helper

# THINK ABOUT

* Thread-safety (triggers from multiple-threads)
* Make triggers not trigger until the whole validator is ready
* Remove data provider marker interface (check validation of heterogeneous groups first)
* Property data provider
* Check licenses again
* Validation flow logging
* Use of IDs
* Use of predicates or compose heterogeneous rules or component state conditional rule or RuleTransformerWrapper/DataProviderTransformerWrapper
* Aggregate boolean result collectors
* Aggregate data from multiple providers, aggregate rule results
* Validator marker interface?
* Heterogeneous Group
* Put decoration per plane and not per parent
* Exception-based results
* Conditional logic framework
* All features described in the README file
* Dialog strategies