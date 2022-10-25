tasks.register<Copy>("installGitHooksFiles") {
    from(File(rootProject.rootDir, "scripts/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks"))
}

tasks.create("installGitHooks") {
    doLast {
        Runtime.getRuntime().exec("chmod -R +x .git/hooks/")
    }
    dependsOn("installGitHooksFiles")
}

tasks.named("clean") {
    dependsOn("installGitHooks")
}
