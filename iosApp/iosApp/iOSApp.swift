import shared
import SwiftUI

@main
struct iOSApp: App {

    init() {
        DependencyInjectionHelper.shared.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ApiKeysListPageView()
		}
	}
}
