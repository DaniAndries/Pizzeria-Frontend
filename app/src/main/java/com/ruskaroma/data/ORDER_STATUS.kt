package com.ruskaroma.data

/**
 * Enum class representing the status of an order in the system.
 * This is used to track the lifecycle of an order from the moment it is placed to its final state.
 */
enum class ORDER_STATUS {

    /**
     * Represents an order that is placed but not yet processed or delivered.
     */
    PENDING,

    /**
     * Represents an order that has been delivered to the customer.
     */
    DELIVERED,

    /**
     * Represents an order that has been canceled and will not be processed.
     */
    CANCELED,

    /**
     * Represents an order that has been finalized and is completed.
     */
    FINALIZED
}
