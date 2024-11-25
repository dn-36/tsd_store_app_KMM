//
//   OrientationHelper.swift
//  iosApp
//
//  Created by Dima Pankov on 25.11.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit

@objc public class OrientationHelper: NSObject {
    @objc public static func setOrientationToPortrait() {
        UIDevice.current.setValue(UIInterfaceOrientation.portrait.rawValue, forKey: "orientation")
    }
}
