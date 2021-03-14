# Basic Checkout

#### A Java 8 application that simulates a store with stock and checkout operations. 
#### Stock items can hold multiple offers and at checkout the most advantageous one is applied



## Run
The code is packaged already in `basic-checkout.jar` and is enough to to be ran under the command:

`java -jar basic-checkout.jar`


## Running Examples


#### Once the application is started up using the aforementioned command the Basic Checkout application does the following:

|Item|Unit Price|Special Price|
|----|----------|-------------|
|A   |50        |3 for 130    |
|B   |30        |2 for 45     |
|C   |50        |             |
|D   |15        |             |

#### 1. It first prompts the user to make stock selections such as:
- load default stock (above table)
- load Skus
- add offers to existing skus
- (once the first sku is added) `Go to checkout` option becomes available
#### 2. Once the first Sku is loaded, another option to go to checkout becomes available and the user can start the process.
#### 3. On the Checkout stage the user can:
- opt to scan products
- complete checkout
- reset scanned list
- exit checkout and applicaition
