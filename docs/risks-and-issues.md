# Risks and Issues

| Issue | Category | Severity | Status | Description |
| :--- | :--- | :--- | :--- | :--- |
| **Busy Waiting** | Performance | **CRITICAL** | Open | Uses `while(thread.isAlive()) { continue; }` to wait for background work. This blocks threads and drains battery. |
| **Hardcoded API Keys** | Security | **HIGH** | Open | Flickr API key is hardcoded in `FlickrApi.java`. |
| **Legacy Storage** | Android Compatibility | **HIGH** | Open | Uses legacy storage permissions which are restricted on Android 13+. |
| **Old Dependencies** | Maintainability | **MEDIUM** | Open | Many libraries (AppCompat, Room, Glide) are significantly outdated. |
| **Missing Tests** | Testing | **MEDIUM** | Open | No meaningful unit or instrumentation tests exist. |
| **Deprecated `isOnline`** | Performance/Reliability | **LOW** | Open | Pinging 8.8.8.8 is an outdated way to check connectivity. |

## Recommended Action for Busy Waiting
Replace manual `Thread` and `while` loops with standard asynchronous patterns:
- `LiveData` / `Flow` (if migrating to Kotlin).
- Callbacks or `ListenableFuture`.
- Standard Room asynchronous queries instead of manual thread blocking.
