# de.bxservice.docstatusvalidator

Document Status Validator iDempiere plug-in.

The goal of the plug-in is to allow the user to create validations on document status changes with SQL conditions. When one of the conditions is fulfilled, an error message is shown to the user explaining what to do to be able to change the document status.

Documentation: https://wiki.idempiere.org/en/Plugin:_Document_Status_Validator.

Video Documentation: https://youtu.be/4Q6i1OOpIDI

## Build

Headless Maven build — no IDE and no local iDempiere checkout required:

```bash
mvn verify
```

Produces `target/de.bxservice.docstatusvalidator-1.0.1-SNAPSHOT.jar`, an OSGi bundle with the
2Pack dictionary content embedded, ready to deploy or to install through the iDempiere Extension
Management form.

**Requirements:** JDK 17 and Maven 3.9+.

### Building against a specific iDempiere version

The build resolves the iDempiere core bundles it requires (`org.adempiere.base`,
`org.adempiere.plugin.utils`, `org.osgi.service.event`) from a p2 repository. By default it uses
the public iDempiere CI repository, which **tracks iDempiere master**:

```
https://jenkins.idempiere.org/job/iDempiere/ws/org.idempiere.p2/target/repository/
```

To build against a particular release, or offline, point it at a local core build:

```bash
mvn verify -Didempiere.core.repository.url=file:///path/to/idempiere/org.idempiere.p2/target/repository
```

That path is produced by building iDempiere core itself (`mvn verify` in the iDempiere source
tree). Note the default is a Jenkins workspace URL, so it can move — the override is the stable
option for reproducible builds.

### Versioning

The bundle version lives in `META-INF/MANIFEST.MF` as `1.0.1.qualifier`; Maven replaces
`qualifier` with a build timestamp, giving e.g. `1.0.1.202609111528`. The `pom.xml` version
(`1.0.1-SNAPSHOT`) must stay in sync with the manifest version — Tycho fails the build otherwise.


Any feedback please in the iDempiere forums. 

Any bug you find please create an issue in this repository.
