# lighthouse plugin for Jenkins CI

Use the awesome Lighthouse to show the state of your builds.

The following states are implemented:

* building => blue
* success => green
* fatal errors => red
* no fatal errors ("unstable") => yellow


## Configuration

1. Open Global Setting and set the
  * IP address of the hue bridge
  * Authorized username of the hue bridge
2. Create a new job or modify an existing job
  * Add post-build action **Colorize Lighthouse**
  * Set the id of the light you want to control
