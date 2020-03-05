require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "RNImagesCombineLibrary"
  s.version      = package['version']
  s.summary      = "RNImagesCombineLibrary"
  s.description  = <<-DESC
                  RNImagesCombineLibrary combine images
                   DESC
  s.homepage     = "https://www.npmjs.com/package/react-native-images-combine"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/react-native-images-combine.git", :tag => "master" }
  s.source_files  = "ios/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  